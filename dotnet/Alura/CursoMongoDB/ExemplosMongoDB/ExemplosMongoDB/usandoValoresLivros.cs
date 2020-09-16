using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Driver;
using MongoDB.Bson;

namespace ExemplosMongoDB
{
    class usandoValoresLivros
    {
        public static async Task MainASync(string[] args)
        {
            //Acessando atraves da classe de conexão
            var conexaoBiblioteca = new conectandoMongoDB();

            // inicializar uma variável do tipo Livro
            //Livro livro = new Livro();
            //livro.Titulo = "Star Wars Legends";
            //livro.Autor = "Timothy Zahn";
            //livro.Ano = 2010;
            //livro.Paginas = 245;
            //List<string> listaAssuntos = new List<string>();
            //listaAssuntos.Add("Ficção Científica");
            //listaAssuntos.Add("Ação");
            //livro.Assunto = listaAssuntos;

            Livro Livro = new Livro();
            Livro = valoresLivro.IncluiValoresLivro("Dom Casmurro", "Machado de Assis", 1923, 188, "Romance, Literatura Brasileira");
            await conexaoBiblioteca.Livros.InsertOneAsync(Livro);
            Livro Livro2 = new Livro();
            Livro2 = valoresLivro.IncluiValoresLivro("A Arte da Ficção", "Daviv Lodge", 2002, 230, "Didático, AutoAjuda");
            await conexaoBiblioteca.Livros.InsertOneAsync(Livro2);

            Console.WriteLine("Documento incluído");
        }
    }
}
