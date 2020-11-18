using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;

namespace ExemplosMongoDB
{
    class listandoDocumentos
    {

        public static async Task MainASync(string[] args)
        {
            //Acessando atraves da classe de conexão
            var conexaoBiblioteca = new conectandoMongoDB();
            Console.WriteLine("Listando Documentos");

            var teste = conexaoBiblioteca.Cliente.GetDatabase("Biblioteca").GetCollection<Livro>("Livros").Find(livro => true).ToList<Livro>();
            Console.WriteLine(teste);
            Console.WriteLine("Listando Documentos");

            var listaLivros = await conexaoBiblioteca.Livros.Find("{}").ToListAsync();

            Console.WriteLine("Inicio da Lista");
            foreach (var doc in listaLivros)
            {
                Console.WriteLine(".");
                Console.WriteLine(doc.ToJson<Livro>());
            }

            Console.WriteLine("Fim da Lista");

        }
    }
}
