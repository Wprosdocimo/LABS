using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;

namespace ExemplosMongoDB
{
    class manipulandoClasses
    {
        public static async Task MainASync(string[] args)
        {
            //var doc = new BsonDocument
            //{
            //    { "Titulo", "Guerra dos Tronos" }
            //};
            //doc.Add("Autor", "George R R Marting");
            //doc.Add("Ano", 1999);
            //doc.Add("Páginas", 856);
            //var assuntoArray = new BsonArray();
            //assuntoArray.Add("Fantasia");
            //assuntoArray.Add("Ação");
            //doc.Add("Assunto", assuntoArray);

            //Console.WriteLine(doc);

            // inicializar uma variável do tipo Livro
            Livro livro = new Livro();

            livro.Titulo = "Sobre a redoma";
            livro.Autor = "Stepahn King";
            livro.Ano = 2012;
            livro.Paginas = 679;
            List<string> listaAssuntos = new List<string>();
            listaAssuntos.Add("Ficção Científica");
            listaAssuntos.Add("Terror");
            listaAssuntos.Add("Ação");
            livro.Assunto = listaAssuntos;


            // Acesso ao servidor MongoDB
            string stringConexao = "mongodb://localhost:27017";
            IMongoClient client = new MongoClient(stringConexao);

            // Acesso ao Banco de dados
            IMongoDatabase bancoDados = client.GetDatabase("Biblioteca");

            // Acesso a Collection
            IMongoCollection<Livro> colecao = bancoDados.GetCollection<Livro>("Livros");

            // Incluindo documento
            await colecao.InsertOneAsync(livro);

            Console.WriteLine("Documento incluído");
        }
    }
}
