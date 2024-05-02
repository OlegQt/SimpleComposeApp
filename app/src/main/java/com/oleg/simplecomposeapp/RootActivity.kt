package com.oleg.simplecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowContactDetails(newContact = getDefaultContactWithImg())
        }

    }

    private fun getDefaultContact(): Contact {
        return Contact(
            name = "John",
            surname = "Brown",
            familyName = "Dostoevsky",
            imageRes = null,
            isFavorite = false,
            phone = "+800 224 00",
            address = "Moscow city",
            email = null,
        )
    }

    private fun getDefaultContactWithImg(): Contact {
        return Contact(
            name = "John",
            surname = "Brown",
            familyName = "Dostoevsky",
            imageRes = R.raw.john_contact,
            isFavorite = false,
            phone = "+800 224 00",
            address = "Moscow city",
            email = null,
        )
    }

    @Composable
    fun ShowContactDetails(newContact: Contact) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25F)
                    .background(color = Color.LightGray)
            ) {
                ShowContactPicture(contact = newContact)
                ShowContactName(
                    nameStr = newContact.name,
                    surnameStr = newContact.surname,
                    familyNameStr = newContact.familyName
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f)
                    .background(Color.Red)
            ) {
            }


        }


    }


    @Composable
    @Preview()
    fun ShowContactDetailsPreview() {
        ShowContactDetails(getDefaultContact())
    }

    @Composable
    @Preview()
    fun ShowContactDetailsPreviewImage() {
        ShowContactDetails(getDefaultContactWithImg())
    }


    @Composable
    fun ShowContactPicture(contact: Contact) {
        when (contact.imageRes) {
            null -> CircleWithInitials(
                nameStr = contact.name,
                surnameStr = contact.surname,
                familyNameStr = contact.familyName
            )

            else -> ShowImage(imageResource = contact.imageRes)
        }
    }

    @Composable
    fun CircleWithInitials(nameStr: String, surnameStr: String?, familyNameStr: String) {
        val nameFirst = nameStr.first()
        val surnameFirst = surnameStr?.first() ?: ""

        Row {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.circle), contentDescription = null

                )
                Text(text = "$nameFirst$surnameFirst")
            }
        }
    }

    @Composable
    fun ShowImage(imageResource: Int) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box (modifier= Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = imageResource), contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(48.dp)
                )
            }
        }
    }

    @Composable
    fun ShowContactName(nameStr: String, surnameStr: String?, familyNameStr: String) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row() {
                Text(
                    text = nameStr.plus(" ").plus(surnameStr),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Row() {
                Text(
                    text = familyNameStr,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}


