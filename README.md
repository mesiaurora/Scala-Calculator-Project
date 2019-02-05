
### Running

```bash
sbt run
```

Play will start up on the HTTP port at <http://localhost:9000/>.   

Run tests: 
```bash
sbt test
```

### Usage


```bash
curl http://localhost:9000/calculus?query=1+2+3
```

Where query is a string of a mathematical expression. 

Supporters operators are: 
* Addition + 
* Subtraction - 
* Multiply * 
* Division / 
* Brackets ( )


Return json contains: 
* Boolean to represent whether there was an error or not 
* Number result if there was no error
* Error message if an error occured



