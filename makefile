# #########################################################

# Arrancar rmiregistry servidor

# #########################################################
rmisetup:
	rmiregistry 54321 &

# #########################################################

# Compilación servidor

# #########################################################
compserver:
	javac servidor/model/*.java
	javac servidor/services/interfaces/*.java
	javac servidor/services/*.java
	javac servidor/ServidorReserva.java

# #########################################################

# Ejecución servidor

# #########################################################
exeserver:
	java -Djava.security.policy=servidor/servidor.permisos servidor/ServidorReserva 54321

# #########################################################

# Compilación cliente

# #########################################################
compclient:
	javac cliente/*.java

# #########################################################

# Ejecución cliente

# #########################################################
execlient:
	java -Djava.security.policy=cliente/cliente.permisos cliente/ClienteReserva localhost 54321
