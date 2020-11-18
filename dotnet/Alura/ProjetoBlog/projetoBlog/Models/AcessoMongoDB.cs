using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MongoDB.Driver;
using MongoDB.Bson;

namespace projetoBlog.Models
{
    public class AcessoMongoDB
    {
        public const string STRING_DE_CONEXAO = "mongodb://localhost:27017";
        public const string NOME_DA_BASE = "Blog";
        public const string NOME_DA_COLECAO_PUBLICACAO = "Publicacoes";
        public const string NOME_DA_COLECAO_USUARIO = "Usuarios";

        private static readonly IMongoClient _cliente;
        private static readonly IMongoDatabase _baseDeDados;

        static AcessoMongoDB()
        {
            _cliente = new MongoClient(STRING_DE_CONEXAO);
            _baseDeDados = _cliente.GetDatabase(NOME_DA_BASE);
        }

        public IMongoClient Cliente
        {
            get { return _cliente; }
        }

        public IMongoCollection<Usuario> Usuarios
        {
            get { return _baseDeDados.GetCollection<Usuario>(NOME_DA_COLECAO_USUARIO); }
        }
        public IMongoCollection<Publicacao> Publicacoes
        {
            get { return _baseDeDados.GetCollection<Publicacao>(NOME_DA_COLECAO_PUBLICACAO); }
        }
    }

}
