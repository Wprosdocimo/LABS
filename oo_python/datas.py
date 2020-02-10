
class Data:
    def __init__(self, dia, mes, ano):
        data = [dia, mes, ano]

    def formatada(self):
        print("{}/{}/{}".format(self.dia,self.mes,self.ano))
