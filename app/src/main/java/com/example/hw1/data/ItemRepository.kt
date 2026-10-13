package com.example.hw1.data

import com.example.hw1.model.Item

object ItemRepository {
    val items: List<Item> = listOf(
        Item(
            id = 1,
            name = "Rockstar Games",
            description = "Создатели GTA и Read Dead Redemption. Известны масштабными открытыми мирами и вниманием к деталям.",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/5631834/551834932/S114x114FitScale_2x"
        ),
        Item(
            id = 2,
            name = "CD Project Red",
            description = "Польская студия, подарившая миру Ведьмака и Cyberpunk 2077. Славится глубоким сюжетом и проработанными RPG.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/CD_Projekt_logo.svg/800px-CD_Projekt_logo.svg.png"
        ),
        Item(
            id = 3,
            name = "Naughty Dog",
            description = "Американская студия, известная серией Uncharted и The Last of Us. Мастера кинематографичного повествования.",
            imageUrl = "https://static.wikia.nocookie.net/bandicoot/images/b/b8/358544.jpg/revision/latest/scale-to-width-down/1200?cb=20140723125000&path-prefix=ru"
        ),
        Item(
            id = 4,
            name = "FromSoftware",
            description = "Японская студия, создавшая Dark Souls, Bloodborne и Elden Ring. Радоначальники жанра соулслайк.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Fromsoftware_logo.svg/800px-Fromsoftware_logo.svg.png"
        ),
        Item(
            id = 5,
            name = "Nintendo",
            description = "Легендарная японская компания, подарившая миру Mario, Zelda и Pokemon. Определяет стандарты игрового дизайна.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Nintendo.svg/800px-Nintendo.svg.png"
        ),
        Item(
            id = 6,
            name = "Valve",
            description = "Американская студия, создавшая Half-Life, Portal и Conter-Strike. Также владеет платформой Steam.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Valve_logo.svg/800px-Valve_logo.svg.png"
        ),
        Item(
            id = 7,
            name = "Santa Monica Studio",
            description = "Американская студия, известная перезапуском God Of War. Славится эпичными сражениями и скандинавской мифологией.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/ru/9/9d/SonySantaMonicaStudioLogo.png"
        ),
        Item(
            id = 8,
            name = "Insomniac Games",
            description = "Американская студия, создавшая Marvel's Spider Man и Ratchet & Clank. Мастера динамичного геймплея.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Insomniac_Games_logo.svg/800px-Insomniac_Games_logo.svg.png"
        ),
        Item(
            id = 9,
            name = "Larian Studios",
            description = "Бельгийская студия, известная Divinity: Original Sin и Baldur's Gate 3. Эталоном современных CRPG",
            imageUrl = "https://upload.wikimedia.org/wikipedia/ru/c/ca/Larian_Studios_Logo.png"
        ),
        Item(
            id = 10,
            name = "Ubisoft",
            description = "Французский гигант, создавший Assassin's Creed, Far Cry, и Rainbow Six. Огромные открытые миры и регулярные релизы.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Ubisoft_logo.svg/800px-Ubisoft_logo.svg.png"
        )
    )
}