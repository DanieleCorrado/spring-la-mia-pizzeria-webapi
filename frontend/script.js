// COSTANTI

const apiUrl = 'http://localhost:8080/api/v1/pizzas'
const root = document.getElementById('root')

// FUNZIONI

// Funzione che renderizza gli ingredienti

const renderIngredients = (ingredients) => {
  console.log(ingredients)
  let content

  if (ingredients.length === 0) {
    content = 'No ingredients'
  } else {
    content = '<ul class="list-unstyled">'
    ingredients.forEach((ing) => {
      content += `<li>${ing.name}</li>`
    })
  }
  return content
}

// Funzione che renderizza la card della pizza

const renderPizza = (element) => {
  console.log(element)

  return `<div class="card shadow h-100">
   <div class=card-body>
    <h5 class="card-title">${element.name}</h5>
    <h6 class="card-subtitle">${element.price} €</h6>
    <p class="card-text">Description: ${
      element.synopsis != '' ? element.description : 'N.D'
    }</p>
   </div>
   <div class="card-footer">
   ${renderIngredients(element.ingredients)}
   </div>
  </div>`
}

// Funzione che renderizza la gallery di card

const renderPizzaList = (data) => {
  let content
  console.log(data)

  if (data.length > 0) {
    // Creo la gallery

    content = '<div class="row">'

    // Itero sull'array di libri

    data.forEach((element) => {
      content += '<div class= col-3>'

      // Chiamo il metodo che restituisce il singolo book

      content += renderPizza(element)
      content += '</div>'
    })

    content += '</div>'
  } else {
    content = '<div class="alert alert-info">The list is empty</div>'
  }

  // Sostituisco il contenuto di root con il content

  root.innerHTML = content
}

// Funzione che chiama l'API e ottiene l'array di pizzas

const getPizza = async () => {
  try {
    const response = await axios.get(apiUrl)

    renderPizzaList(response.data)
  } catch (error) {
    console.log(error)
  }
}

getPizza()
