
class Conta:
    def __init__(self, numero, titular, saldo, limite=1000.00):
        print("Contruindo objeto ... {}".format(self))
        self.numero = numero
        self.titular = titular
        self.saldo = saldo
        self.limite = limite
