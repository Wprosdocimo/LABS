using System;
using Microsoft.IdentityModel.Tokens;

namespace Alura.ListaLeitura.Seguranca
{
    public class SecurityKey
    {
        public static implicit operator SecurityKey(SymmetricSecurityKey v)
        {
            throw new NotImplementedException();
        }
    }
}