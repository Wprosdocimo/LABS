namespace Alura.ListaLeitura.Seguranca
{
    public class SigningCredentials
    {
        private SecurityKey key;
        private object hmacSha256;

        public SigningCredentials(SecurityKey key, object hmacSha256)
        {
            this.key = key;
            this.hmacSha256 = hmacSha256;
        }
    }
}