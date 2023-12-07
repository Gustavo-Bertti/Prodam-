localStorage.clear();
async function enviarParaServidor(url) {
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error(`Erro na solicitação: ${response.status}`);
        }

        const responseBody = await response.json();
        console.log('Resposta completa do servidor:', responseBody);

        
        return responseBody; 
    } catch (error) {
        console.error('Erro:', error);
        throw error;
    }
}
async function deletePorId(id) {
    const url = `http://localhost:8080/medicamento/delete/${id}`;

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro na solicitação: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
       
        if (data) {
            console.log(data);
        } else {
            console.error('Erro: A resposta não contém dados JSON válidos.');
        }
    })
    .catch(error => console.error('Erro:', error));
    location.reload(true);
}

async function editarPorId(id) {
    try {
        // Obter informações do medicamento pelo ID
        const medicamento = await enviarParaServidor(`http://localhost:8080/medicamento/${id}`);
        localStorage.setItem('medicamento',JSON.stringify(medicamento));
        
        // Redirecionar para a tela de edição com as informações do medicamento
        window.location.href = `/Client/assets/Pages/RegistroMedicamento/editar/editar.html`;
        console.log(medicamento);
    } catch (error) {
        console.error('Erro ao obter medicamento para edição:', error);
    }
}



// Exemplo de uso
async function obterEMostrarMedicamentos() {
    try {
        const respostaServidor = await enviarParaServidor("http://localhost:8080/medicamento");

        console.log('Resposta completa do servidor:', respostaServidor);

        
        const listaMedicamentos = respostaServidor;

        if (Array.isArray(listaMedicamentos) && listaMedicamentos.length > 0) {
            
            const contentMain = document.querySelector('.content__main');

           
            listaMedicamentos.forEach(medicamento => {
                const div = document.createElement('div');
                const paragrafo = document.createElement('p');
                paragrafo.textContent = `ID: ${medicamento.id},Nome: ${medicamento.nome}, Dosagem: ${medicamento.dosagem}, Forma: ${medicamento.forma}, Fabricante: ${medicamento.fabricante}`;
                const btn = document.createElement('button');
                btn.textContent = 'Editar';
                const btn_delete = document.createElement('button');
                btn_delete.textContent = 'Excluir';
                btn.addEventListener('click', () => editarPorId(medicamento.id));
                btn_delete.addEventListener('click', () => deletePorId(medicamento.id));


                contentMain.appendChild(div);
                div.classList.add(`conteudo`);
                div.appendChild(paragrafo);
                div.appendChild(btn);
                div.appendChild(btn_delete);
            });
        } else {
            console.error('A lista de medicamentos está vazia ou não é uma lista válida.');
        }
    } catch (error) {
        
        console.error('Erro ao obter medicamentos:', error);
    }
}


obterEMostrarMedicamentos();




