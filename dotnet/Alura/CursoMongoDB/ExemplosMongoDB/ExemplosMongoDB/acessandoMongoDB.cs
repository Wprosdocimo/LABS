using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;

namespace ExemplosMongoDB
{
    class acessandoMongoDB
    {
        //static void Main(string[] args)
        //{
        //    Task T = MainASync(args);
        //    Console.WriteLine("Precione ENTER");
        //    Console.ReadLine();
        //}

        public static async Task MainASync(string[] args)
        {
            var doc = new BsonDocument
            {
                { "Titulo", "Guerra dos Tronos" }
            };
            doc.Add("Autor", "George R R Marting");
            doc.Add("Ano", 1999);
            doc.Add("Páginas", 856);
            var assuntoArray = new BsonArray();
            assuntoArray.Add("Fantasia");
            assuntoArray.Add("Ação");
            doc.Add("Assunto", assuntoArray);

            Console.WriteLine(doc);

            // Acesso ao servidor MongoDB
            string stringConexao = "mongodb://localhost:27017";
            IMongoClient client = new MongoClient(stringConexao);

            // Acesso ao Banco de dados
            IMongoDatabase bancoDados = client.GetDatabase("Biblioteca");

            // Acesso a Collection
            IMongoCollection<BsonDocument> colecao = bancoDados.GetCollection<BsonDocument>("Livros");

            // Incluindo documento
            await colecao.InsertOneAsync(doc);

            Console.WriteLine("Documento incluído");
        }


    }
}
