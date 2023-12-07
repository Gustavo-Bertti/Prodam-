document.getElementById('meuFormulario').addEventListener('submit', async function (event) {
    event.preventDefault(); // Evita que o formulário seja enviado da maneira tradicional

    // Função para obter o valor de um campo de entrada por nome
    function obterValorDoCampo(nome) {
        var elemento = document.querySelector('input[name="' + nome + '"]');
        return elemento ? elemento.value : null;
    }

    // Coletando dados do formulário
    var formDataFornecedor = {
        nome: obterValorDoCampo("nome_fornecedor"),
        telefone: obterValorDoCampo("telefone_fornecedor"),
        endereco: obterValorDoCampo("endereco_fornecedor"),
    };

    var formDataCategoria = {
        nome: obterValorDoCampo("nome_categoria"),
        descricao: obterValorDoCampo("descricao_categoria"),
    };

    

  

    // Convertendo dados para JSON
    var jsonDataFornecedor = JSON.stringify(formDataFornecedor);
    var jsonDataCategoria = JSON.stringify(formDataCategoria);

    // Enviando dados para o backend
    enviarParaServidor("http://127.0.0.1:8080/fornecedor", jsonDataFornecedor);
    enviarParaServidor("http://127.0.0.1:8080/categoria", jsonDataCategoria);

    try {
        let idFornecedor = 0;
        let idCategoria = 0;
        // cronometro para dar tempo do sistema fazer o post, para depois fazer o get
        for(let i = 0; i < 100000; i++){
            if(i == 99999){
        const idFornecedorF = await enviarParaServidorGetFornecedor("http://localhost:8080/fornecedor", formDataFornecedor);
        console.log('ID do Fornecedor:', idFornecedorF);
        idFornecedor = idFornecedorF;
        
        const idCategoriaF = await enviarParaServidorGetCategoria("http://127.0.0.1:8080/categoria", formDataCategoria);
        console.log('ID da Categoria:', idCategoriaF);
        idCategoria = idCategoriaF;
        }}
        console.log(idFornecedor);
        console.log(idCategoria);
        var formDataMedicamento = {
            nome: obterValorDoCampo("nome_medicamento"),
            dosagem: obterValorDoCampo("dosagem_medicamento"),
            forma: obterValorDoCampo("forma_medicamento"),
            fabricante: obterValorDoCampo("fabricante_medicamento"),
            data: obterValorDoCampo("data_medicamento"),
            idFornecedor: idFornecedor,
            idCategoria: idCategoria
        };

        console.log(formDataMedicamento);

        await enviarParaServidorMedicamento("http://127.0.0.1:8080/medicamento", JSON.stringify(formDataMedicamento));
        alert('Cadastro do medicamento feito com sucesso')
        window.location.href="http://127.0.0.1:5500/Client/index.html";
    } catch (error) {
        console.error('Erro ao enviar para o servidor:', error.message);
    }
});

async function enviarParaServidor(url, dados) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: dados,
    });

    if (!response.ok) {
        throw new Error(`Erro na solicitação: ${response.status}`);
    }

    const responseBody = await response.json();
    console.log('Resposta do servidor:', responseBody);
    return responseBody.id;
}

async function enviarParaServidorMedicamento(url, dados) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: dados,
    });

    if (!response.ok) {
        throw new Error(`Erro na solicitação: ${response.status}`);
    }

    const responseBody = await response.json();
    console.log('Resposta do servidor:', responseBody);

    
    if ('id' in responseBody) {
        return responseBody.id;
    } else {
        console.warn('A resposta do servidor não contém um campo "id". Verifique a implementação do backend.');
        return null;
    }
}



async function enviarParaServidorGetFornecedor(url, dados) {
    var urlComParametros = `${url}/${dados.nome}-${dados.telefone}-${dados.endereco}`;

    try {
        const response = await fetch(urlComParametros, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`Erro na solicitação: ${response.status}`);
        }

        var id = parseInt(await response.text());

        console.log('ID retornado:', id);
        return id;
    } catch (error) {
        console.error('Erro na solicitação:', error.message);
        throw error;
    }
}

async function enviarParaServidorGetCategoria(url, dados) {
   
    var urlComParametros = `${url}/${dados.nome}-${dados.descricao}`;

    try {
        const response = await fetch(urlComParametros, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`Erro na solicitação: ${response.status}`);
        }

        
        var id = parseInt(await response.text());

        console.log('ID retornado Categoria:', id);
        return id;
    } catch (error) {
        console.error('Erro na solicitação:', error.message);
        throw error;
    }
}





