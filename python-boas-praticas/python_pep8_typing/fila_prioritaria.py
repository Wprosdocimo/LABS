from typing import Dict, Union, List

from estatistica_detalhada import EstatisticaDetalhada
from estatistica_resumida import EstatisticaResumida
from fila_base import FilaBase
from constantes import CODIGO_PRIORITARIO

Classes = Union[EstatisticaDetalhada, EstatisticaResumida]


class FilaPrioritaria(FilaBase):

    def gera_senha_atual(self):
        self.senha_atual = f'{CODIGO_PRIORITARIO}{self.codigo}'

    def chama_cliente(self, caixa: int) -> str:
        cliente_atual = self.fila.pop(0)
        display = f'Cliente: {cliente_atual} - Caixa {caixa}'
        self.clientes_atendidos.append(cliente_atual)
        return display

    def estatistica(self, retorna_estatistica: Classes) -> dict:
        return retorna_estatistica.roda_estatistica(self.clientes_atendidos)
