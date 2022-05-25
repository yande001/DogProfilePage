package com.example.darren.dogprofilepage


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ProfilePage(){
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))

    ) {
        BoxWithConstraints() {
            val constraints = if(minWidth < 600.dp) {
                portraitConstraints(margin = 16.dp)
            } else{
                landscapeConstraints(margin = 16.dp)
            }

            ConstraintLayout(constraints){


                Image(painter= painterResource(id = R.drawable.little_house),
                    contentDescription = "Dog",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .layoutId("image"),
                    contentScale = ContentScale.Crop

                )
                Text(
                    text = "Shih Tzu",
                    modifier = Modifier.layoutId("breedText")

                )
                Text(
                    text = "Little House",
                    modifier = Modifier.layoutId("nameText"),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")
                ) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Followings")
                    ProfileStats(count = "20", title = "Posts")

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("buttonFollow")
                ) {
                    Text(text = "Follow user")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("buttonMessage")
                ) {
                    Text(text = "Direct Message")
                }

            }

        }

    }
}

@Composable
fun ProfileStats(count: String, title: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val breedText = createRefFor("breedText")
        val nameText = createRefFor("nameText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        val guideline = createGuidelineFromTop(0.3f)
        constrain(image){
            top.linkTo(guideline, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
        }
        constrain(breedText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText){
            top.linkTo(breedText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats){
            top.linkTo(breedText.bottom, margin = 8.dp)
        }
        constrain(buttonFollow){
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(buttonMessage.start)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage){
            top.linkTo(rowStats.bottom,  margin = 16.dp)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstraints(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val breedText = createRefFor("breedText")
        val nameText = createRefFor("nameText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(breedText){
            top.linkTo(image.bottom)
            start.linkTo(image.start, margin = margin)
        }
        constrain(nameText){
            top.linkTo(breedText.bottom)
            start.linkTo(breedText.start)
            end.linkTo(breedText.end)
        }
        constrain(rowStats){
            top.linkTo(image.top)
            start.linkTo(image.start, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(buttonFollow){
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(rowStats.start)
            end.linkTo(buttonMessage.end)
            bottom.linkTo(nameText.bottom)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage){
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(nameText.bottom)
            width = Dimension.wrapContent
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview(){
    ProfilePage()
}