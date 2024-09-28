package com.example.contactlist2

import androidx.annotation.DrawableRes

sealed class ContactImage {
    data class Url(val url : String) : ContactImage()
    data class Drawable(@DrawableRes val resId : Int) : ContactImage()
}

data class ContactInfo(val name : String, val phoneNO : String, val address : String, val image: ContactImage)

fun getFakeData(): List<ContactInfo> {
    return listOf(
        ContactInfo(
            "Mohammed Ali",
            "053211111",
            "Istanbul 123 TR",
            image = ContactImage.Drawable(resId = R.drawable.person1)
        ),
        ContactInfo(
            "Ahmad Hamada",
            "012345678",
            "Syria 511 SY",
            image = ContactImage.Drawable(resId = R.drawable.person2)
        ),

        ContactInfo(
            "Mary Mobas",
            "0432111000",
            "Egypt 611 EP",
            image = ContactImage.Drawable(R.drawable.person3)
        ),
        ContactInfo(
            "John Doe",
            "0522334455",
            "New York 10001 US",
            image = ContactImage.Drawable(R.drawable.person4)
        ),

        ContactInfo(
            "Carlos Hernandez",
            "0912345678",
            "Madrid 28001 ES",
            image = ContactImage.Drawable(R.drawable.person5)
        ),
        ContactInfo(
            "Jane Smith",
            "0721345789",
            "London 4567 UK",
            image = ContactImage.Url("https://robohash.org/stefan-one")
        ),
        ContactInfo(
            "Carlos Hernandez",
            "0912345678",
            "Madrid 28001 ES",
            image = ContactImage.Drawable(R.drawable.person5)
        ),
        ContactInfo(
            "Takashi Yamada",
            "0801234567",
            "Tokyo 100-0001 JP",
            image = ContactImage.Url(url = "https://i.redd.it/rwl5o5xm2tv71.jpg")
        ),
        ContactInfo(
            "Olga Ivanova",
            "0951234567",
            "Moscow 101000 RU",
            image = ContactImage.Url(url = "https://img.freepik.com/free-photo/front-view-smiley-woman-with-earbuds_23-2148613052.jpg")
        ),
        ContactInfo(
            "Hans Müller",
            "04912345678",
            "Berlin 10115 DE",
            image = ContactImage.Url(url = "https://xsgames.co/randomusers/assets/avatars/male/46.jpg")
        ),
        ContactInfo(
            "Pierre Dubois",
            "0678123456",
            "Paris 75001 FR",
            image = ContactImage.Url(url = "https://xsgames.co/randomusers/assets/avatars/male/74.jpg")
        )
    )
}
