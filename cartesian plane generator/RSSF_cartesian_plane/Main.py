
from Inicializacao.Codigo import (GeraPlanoCartesiano as gpc)

def main():
    '''
    Insert a mote quantity - consider the first input to central base.
    '''
    motes = int(input('Quantidade de motes: '))

    gpc.planoCartesiano(motes)


'''Call main function'''
if __name__ == '__main__':
    main() #substituir por sys.argv[1]