const dados = localStorage.getItem('medicamento');
const medicamento = JSON.parse(dados);
console.log(medicamento);

function pegarElemento(id) {
    elemento = document.getElementById(id);
    return elemento;
}

function obterValorDoCampo(nome) {
    var elemento = document.querySelector('input[name="' + nome + '"]');
    return elemento ? elemento.value : null;
}
function formatarData(dataString) {
   
    const data = new Date(dataString);

  
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0'); 
    const ano = data.getFullYear();

   
    const dataFormatada = `${dia}/${mes}/${ano}`;

    return dataFormatada;
}
const nome = pegarElemento('nome');
const dosagem = pegarElemento('dosagem');
const forma = pegarElemento('forma');
const fabricante = pegarElemento('fabricante');
const data = pegarElemento('data');

const dataFormatada = formatarData(medicamento.data.split(' ')[0]);

nome.value = medicamento.nome;
dosagem.value = medicamento.dosagem;
forma.value = medicamento.forma;
fabricante.value = medicamento.fabricante;
data.value = dataFormatada;



async function atualizarMedicamentoNoServidor(id, dadosAtualizados) {
    const url = `http://localhost:8080/medicamento/${id}`;
    
    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dadosAtualizados),
        });

        if (!response.ok) {
            throw new Error(`Erro na solicitação: ${response.status}`);
        }

        const medicamentoAtualizado = await response.json();
        console.log('Medicamento atualizado com sucesso:', medicamentoAtualizado);
        return medicamentoAtualizado.id; 
    } catch (error) {
        console.error('Erro ao atualizar medicamento:', error);
        throw error;
    }
}

document.getElementById('meuFormulario').addEventListener('submit', async function (event) {
    event.preventDefault();

    var formDataMedicamento = {
        id: medicamento.id,
        nome: obterValorDoCampo("nome_medicamento"),
        dosagem: obterValorDoCampo("dosagem_medicamento"),
        forma: obterValorDoCampo("forma_medicamento"),
        fabricante: obterValorDoCampo("fabricante_medicamento"),
        data: obterValorDoCampo("data_medicamento"),
        idFornecedor: medicamento.idFornecedor,
        idCategoria: medicamento.idCategoria

    };
    
    console.log(formDataMedicamento);

    try {
        const idAtualizado = await atualizarMedicamentoNoServidor(medicamento.id, formDataMedicamento);
        console.log(`Medicamento atualizado com sucesso. ID: ${idAtualizado}`);
        alert(`Medicamento atualizado com sucesso. ID: ${idAtualizado}`)
         window.location.href = "http://127.0.0.1:5500/Client/assets/Pages/RegistroMedicamento/Registro.html";
    } catch (error) {
        console.error('Erro ao atualizar medicamento:', error);
    }





});
