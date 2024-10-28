# Examen DevSecOps


# Partie 1 : Basic CRUD Operations

## la base de  donnes distant
pour cela j'ai utilise une container docker pour simuler une base de donnees distant aven la command :
```bash
docker run -d \
    --name mysql-devsecops \
    -p 3306:3306 \
    -e MYSQL_DATABASE=examdevsecops \
    -e MYSQL_USER=yassine \
    -e MYSQL_PASSWORD=eljakani \
    -e MYSQL_ROOT_PASSWORD=root_password \
    mysql:8.0
```
## pour tester les opperation CRUD basiques jai utilise ces commandes curl

### Ajout dun employee

```bash
curl -X POST http://localhost:8080/employees \
-H "Content-Type: application/json" \
-d '{
    "name": "Yassine El Jakani",
    "email": "yassine@eljakani.me",
    "salary": 50000
}'
```

### Affichage de tous les employés

```bash
curl -X GET http://localhost:8080/employees
```
### Affichage d'un employé par ID

```bash
curl -X GET http://localhost:8080/employees/1
```
### Mise à jour d'un employé

```bash
curl -X PUT http://localhost:8080/employees \
-H "Content-Type: application/json" \
-d '{
    "id": 1,
    "name": "Yassine El Jakani Updated",
    "email": "yassine.updated@eljakani.me",
    "salary": 55000
}'
```
### Suppression d'un employé
```bash
curl -X DELETE http://localhost:8080/employees/1
```