using System;
using System.Collections.Generic;
using System.Text;
using MongoDB.Driver;
using MongoDB.Bson;
using System.Threading.Tasks;

namespace ExemplosMongoDB
{
    class incluindoMuitosLivros
    {

        public static async Task MainASync(string[] args)
        {
            //Acessando atraves da classe de conexão
            var conexaoBiblioteca = new conectandoMongoDB();

            List<Livro> Livros = new List<Livro>();
            Livros.Add(valoresLivro.IncluiValoresLivro("A Dança com os Dragões", "George R R Martin", 2011, 934, "Fantasia, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("A Tormenta das Espadas", "George R R Martin", 2006, 1276, "Fantasia, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1915, 267, "Literatura Brasileira"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Star Trek Portal do Tempo", "Crispin A C", 2002, 321, "Fantasia, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Star Trek Enigmas", "Dedopolus Tim", 2006, 195, "Ficção Científica, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Emília no Pais da Gramática", "Monteiro Lobato", 1936, 230, "Infantil, Literatura Brasileira, Didático"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Chapelzinho Amarelo", "Chico Buarque", 2008, 123, "Infantil, Literatura Brasileira"));
            Livros.Add(valoresLivro.IncluiValoresLivro("20000 Léguas Submarinas", "Julio Verne", 1894, 256, "Ficção Científica, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Primeiros Passos na Matemática", "Mantin Ibanez", 2014, 190, "Didático, Infantil"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Saúde e Sabor", "Yeomans Matthew", 2012, 245, "Culinária, Didático"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Goldfinger", "Iam Fleming", 1956, 267, "Espionagem, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("Da Rússia com Amor", "Iam Fleming", 1966, 245, "Espionagem, Ação"));
            Livros.Add(valoresLivro.IncluiValoresLivro("O Senhor dos Aneis", "J R R Token", 1948, 1956, "Fantasia, Ação"));

            await conexaoBiblioteca.Livros.InsertManyAsync(Livros);


            Console.WriteLine("Documento incluído");
        }
    }
}
