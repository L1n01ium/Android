package com.example.hw1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun SecondScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val locationManager = remember {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    var locationText by rememberSaveable {
        mutableStateOf(context.getString(R.string.location_hint))
    }
    var locationListener by remember { mutableStateOf<LocationListener?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (result[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            result[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            requestLocation(context, locationManager, { locationText = it }, { locationListener = it })
        } else {
            locationText = context.getString(R.string.location_denied)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            locationListener?.let {
                try { locationManager.removeUpdates(it) } catch (_: SecurityException) {}
            }
        }
    }

    fun onGetLocationClick() {
        locationListener?.let {
            try { locationManager.removeUpdates(it) } catch (_: SecurityException) {}
        }
        locationListener = null

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            requestLocation(context, locationManager, { locationText = it }, { locationListener = it })
        } else {
            permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextButton(onClick = onBack) {
            Text(stringResource(R.string.back))
        }
        Text(stringResource(R.string.second_screen_title), style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = locationText,
            onValueChange = {},
            readOnly = true,
            minLines = 3,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onGetLocationClick() }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.btn_get_location))
        }
    }
}

@Suppress("DEPRECATION")
fun requestLocation(
    context: Context,
    locationManager: LocationManager,
    onTextChange: (String) -> Unit,
    onListenerChange: (LocationListener?) -> Unit
) {
    onTextChange(context.getString(R.string.location_hint))
    val provider = if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) LocationManager.GPS_PROVIDER else LocationManager.NETWORK_PROVIDER

    try {
        val listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                onTextChange(context.getString(R.string.location_format, location.latitude, location.longitude))
                locationManager.removeUpdates(this)
                onListenerChange(null)
            }
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        onListenerChange(listener)
        locationManager.requestSingleUpdate(provider, listener, Looper.getMainLooper())
    } catch (_: SecurityException) {
        onTextChange(context.getString(R.string.location_denied))
    }
}