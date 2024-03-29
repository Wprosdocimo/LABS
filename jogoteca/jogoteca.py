from flask import Flask, render_template, request, redirect, session, flash, url_for
from models import Jogo, Usuario
from dao import JogoDao
from flask_mysqldb import MySQL

app = Flask(__name__)
app.secret_key = 'wprosdocimo'

app.config['MYSQL_HOST'] = "127.0.0.1"
app.config['MYSQL_USER'] = "root"
app.config['MYSQLPASSWORD'] = "nosliw"
app.config['MYSQL_DB'] = "jogoteca"
app.config['MYSQL_PORT'] = 3306
db = MySQL(app)
jogo_dao = JogoDao(db)


jogo1 = Jogo('Super Mario', 'Acao', 'SNES')
jogo2 = Jogo('Pokemon Gold', 'RPG', 'GBA')
jogo3 = Jogo('Mortal Kombat', 'Luta', 'SNES')
lista = [jogo1, jogo2, jogo3]
usuario1 = Usuario('luan', 'Luiz Antonio Marques', '1234')
usuario2 = Usuario('Nico', 'Nico Steppat', '7a1')
usuario3 = Usuario('flavio', 'flavio Almeida', 'javascript')
usuarios = {usuario1.id: usuario1,
            usuario2.id: usuario2,
            usuario3.id: usuario3}


@app.route("/")
def index():
    return render_template("lista.html", titulo="Jogos", jogos=lista)


@app.route('/novo')
def novo():
    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        return redirect(url_for('login', proxima=url_for('novo')))
    return render_template('novo.html', titulo='Novo Jogo')


@app.route('/criar', methods=['POST',])
def criar():
    nome = request.form['nome']
    categoria = request.form['categoria']
    console = request.form['console']
    jogo = Jogo(nome, categoria, console)
    jogo_dao.salvar(jogo)
    return redirect(url_for('index'))


@app.route('/login')
def login():
    proxima = request.args.get('proxima')
    return render_template('login.html', proxima=proxima)


@app.route('/autenticar', methods=['POST', ])
def autenticar():
    if request.form['usuario'] in usuarios:
        usuario = usuarios[request.form['usuario']]
        if usuario.senha == request.form['senha']:
            session['usuario_logado'] = usuario.id
            flash(usuario.nome + ' logou com sucesso!')
            proxima_pagina = request.form['proxima']
            return redirect(proxima_pagina)
    flash('Não logado, tente novamente!')
    return redirect(url_for('login'))


@app.route('/logout')
def logout():
    session['usuario_logado'] = None
    flash('Nenhum usuário logado!')
    return redirect(url_for('index'))


app.run(debug=True)
