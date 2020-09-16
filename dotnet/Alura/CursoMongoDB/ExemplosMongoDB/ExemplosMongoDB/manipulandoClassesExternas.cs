using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Driver;
using MongoDB.Bson;

namespace ExemplosMongoDB
{
    class manipulandoClassesExternas
    {
        public static async Task MainASync(string[] args)
        {

            // inicializar uma variável do tipo Livro
            Livro livro = new Livro();

            livro.Titulo = "Star Wars Legends";
            livro.Autor = "Timothy Zahn";
            livro.Ano = 2010;
            livro.Paginas = 245;
            List<string> listaAssuntos = new List<string>();
            listaAssuntos.Add("Ficção Científica");
            listaAssuntos.Add("Ação");
            livro.Assunto = listaAssuntos;


            //// Acesso ao servidor MongoDB
            //string stringConexao = "mongodb://localhost:27017";
            //IMongoClient client = new MongoClient(stringConexao);

            //// Acesso ao Banco de dados
            //IMongoDatabase bancoDados = client.GetDatabase("Biblioteca");

            //// Acesso a Collection
            //IMongoCollection<Livro> colecao = bancoDados.GetCollection<Livro>("Livros");

            //Acessando atraves da classe de conexão

            var conexaoBiblioteca = new conectandoMongoDB();

            // Incluindo documento
            await conexaoBiblioteca.Livros.InsertOneAsync(livro);

            Console.WriteLine("Documento incluído");
        }
    }
}
