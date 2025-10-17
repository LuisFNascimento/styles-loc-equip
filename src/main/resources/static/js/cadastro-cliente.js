const form = document.getElementById("clienteForm");
const successMsg = document.getElementById("successMsg");
const errorMsg = document.getElementById("errorMsg");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const cliente = {
    nome: document.getElementById("nome").value,
    cpf: document.getElementById("cpf").value,
    telefone: document.getElementById("telefone").value,
    email: document.getElementById("email").value,
    endereco: {
      cep: document.getElementById("cep").value,
      logradouro: document.getElementById("logradouro").value,
      numero: document.getElementById("numero").value,
      complemento: document.getElementById("complemento").value,
      bairro: document.getElementById("bairro").value,
      cidade: document.getElementById("cidade").value,
      estado: document.getElementById("estado").value
    }
  };

  try {
    const response = await fetch("http://localhost:8080/clientes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(cliente)
    });

    if (response.ok) {
      const clienteCadastrado = await response.json(); // Pegando o objeto cadastrado retornado pelo backend
      successMsg.textContent = `Cliente "${clienteCadastrado.nome}" cadastrado com sucesso!`;
      successMsg.style.display = "block";
      errorMsg.style.display = "none";
      form.reset();
    } else {
      throw new Error("Erro ao cadastrar cliente");
    }
  } catch (error) {
    console.error(error);
    successMsg.style.display = "none";
    errorMsg.style.display = "block";
  }
});
