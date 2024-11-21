package com.ruskaroma.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composePizzeria.data.MEAT_TYPE
import com.ruskaroma.R
import com.ruskaroma.data.ProductDTO
import com.ruskaroma.data.SIZE
import com.ruskaroma.data.TYPE
import com.ruskaroma.ui.theme.RuskaRomaTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    viewModel.generateList()
    val productList by viewModel.productList.observeAsState()
    val totalProducts by viewModel.totalProducts.observeAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xD081555C)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBar(totalProducts)
        }
        productList?.let { list ->
            item {
                Text(
                    "Pizzas",
                    Modifier.padding(16.dp),
                    color = Color(0xFFDAD0D3),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                LazyRow {
                    items(list.filter { it.type == TYPE.PIZZA }) { product ->
                        ProductCard(product,
                            onAddCart = { amount, product -> viewModel.onAddCart(amount, product) })
                    }
                }
            }

            item {
                Text(
                    "Pasta",
                    Modifier.padding(16.dp),
                    color = Color(0xFFDAD0D3),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                LazyRow {
                    items(list.filter { it.type == TYPE.PASTA }) { product ->
                        ProductCard(product,
                            onAddCart = { amount, product -> viewModel.onAddCart(amount, product) })
                    }
                }
            }

            item {
                Text(
                    "Kebabs",
                    Modifier.padding(16.dp),
                    color = Color(0xFFDAD0D3),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                LazyRow {
                    items(list.filter { it.type == TYPE.KEBAB }) { product ->
                        ProductCard(product,
                            onAddCart = { amount, product -> viewModel.onAddCart(amount, product) })
                    }
                }
            }

            item {
                Text(
                    "Bebidas",
                    Modifier.padding(16.dp),
                    color = Color(0xFFDAD0D3),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                LazyRow {
                    items(list.filter { it.type == TYPE.DRINK }) { product ->
                        ProductCard(product,
                            onAddCart = { amount, product -> viewModel.onAddCart(amount, product) })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(totalProducts: Int?) {
    TopAppBar(title = {
        Text(
            text = "Ruska Roma",
            style = MaterialTheme.typography.titleLarge,
        )
    }, navigationIcon = {
        IconButton(onClick = { /* Acción del menú */ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color(0xFFDAD0D3)
            )
        }
    }, actions = {
        BadgedBox(badge = {
            Badge(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Text(
                    text = totalProducts?.toString() ?: "0",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }) {
            IconButton(onClick = { /* Acción del carrito */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito",
                    tint = Color(0xFFDAD0D3)
                )
            }
        }
    }, colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = Color(0xFF6E1B3A),
        titleContentColor = Color(0xFFDAD0D3),
        actionIconContentColor = Color(0xFFDAD0D3)
    )
    )
}


@Composable
fun ProductCard(product: ProductDTO, onAddCart: (amount: Int, product: ProductDTO) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .size(300.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val productImage = when (product.type) {
                TYPE.PIZZA -> R.drawable.pizza
                TYPE.PASTA -> R.drawable.pasta
                TYPE.KEBAB -> R.drawable.durum
                TYPE.DRINK -> R.drawable.drinks
            }
            Image(
                painter = painterResource(id = productImage),
                contentDescription = product.name,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = product.name)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "${product.price}€")

            val description = when (product.type) {
                TYPE.PIZZA -> product.ingredients.joinToString { it.name }
                TYPE.PASTA -> product.ingredients.joinToString { it.name }
                TYPE.KEBAB -> product.ingredients.joinToString { it.name }
                TYPE.DRINK -> ""
            }
            Text(text = description, fontSize = 15.sp)

            Spacer(modifier = Modifier.height(8.dp))

            SetButtons(product.type, product, onAddCart)
        }
    }
}

@Composable
fun SetButtons(
    type: TYPE, product: ProductDTO, onAddCart: (Int, ProductDTO) -> Unit
) {
    var counter by rememberSaveable { mutableIntStateOf(1) }
    var selectedSize by rememberSaveable { mutableStateOf("Size") }
    var selectedMeat by rememberSaveable { mutableStateOf("Meat") }

    val isButtonEnabled = when (type) {
        TYPE.PASTA -> true // PASTA
        TYPE.KEBAB -> product.size != null && product.meat != null // KEBAB
        else -> product.size != null // OTHERS
    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { if (counter > 1) counter-- }, modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.RemoveCircle, contentDescription = "Decrement"
                )
            }

            Text(
                text = counter.toString()
            )

            IconButton(
                onClick = { counter++ }, modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle, contentDescription = "Increment"
                )
            }
        }
        if (type != TYPE.PASTA) {
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

                SetDropdownMenus(type, product, selectedSize, selectedMeat) { size, meat ->
                    selectedSize = size
                    selectedMeat = meat
                }

            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        var context = LocalContext.current
        IconButton(enabled = isButtonEnabled, onClick = {
            onAddCart(counter, product)
            Toast.makeText(
                context, "You have added $counter products!", Toast.LENGTH_SHORT
            ).show()
            product.size = null
            product.meat = null
            selectedSize = "Size"
            selectedMeat = "Meat"
            counter = 1

        }) {
            Icon(imageVector = Icons.Filled.AddShoppingCart, contentDescription = "Add to Cart")
        }
    }
}

@Composable
fun SetDropdownMenus(
    type: TYPE,
    product: ProductDTO,
    selectedSize: String,
    selectedMeat: String,
    onSizeAndMeatChange: (String, String) -> Unit
) {
    var expandedSize by rememberSaveable { mutableStateOf(false) }
    var expandedMeat by rememberSaveable { mutableStateOf(false) }

    TextButton(onClick = { expandedSize = true }, modifier = Modifier.width(83.dp)) {
        Text(selectedSize)
        DropdownMenu(expanded = expandedSize, onDismissRequest = { expandedSize = false }) {
            for (size in SIZE.entries) {
                DropdownMenuItem(text = { Text(size.toString()) }, onClick = {
                    product.size = size
                    onSizeAndMeatChange(size.toString(), selectedMeat)
                    expandedSize = false
                })
            }
        }
    }

    if (type == TYPE.KEBAB) {
        TextButton(onClick = { expandedMeat = true }, modifier = Modifier.width(83.dp)) {
            Text(selectedMeat)
            DropdownMenu(expanded = expandedMeat, onDismissRequest = { expandedMeat = false }) {
                for (meat in MEAT_TYPE.entries) {
                    DropdownMenuItem(text = { Text(meat.toString()) }, onClick = {
                        product.meat = meat
                        onSizeAndMeatChange(selectedSize, meat.toString())
                        expandedMeat = false
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    RuskaRomaTheme {
        HomeScreen(HomeViewModel())
    }
}