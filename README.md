
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
* Addition %2B 
    * Due to encoding, addition cannot be done with + and %2B has to be used
* Subtraction - 
* Multiply * 
* Division / 
* Brackets ( )


Return json contains: 
* Boolean to represent whether there was an error or not 
* Number result if there was no error
* Error message if an error occured



