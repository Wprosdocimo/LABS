using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using MongoDB.Bson;

namespace ExemplosMongoDB
{
    class manipulandoDocumentos
    {
        //static void Main(string[] args)
        //{
        //    Task T = MainASync(args);
        //    Console.WriteLine("Precione ENTER");
        //    Console.ReadLine();
        //}

        static async Task MainASync2(string[] args)
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
            
            await Task.Delay(100);

            Console.WriteLine(doc);
        }
    }
}
