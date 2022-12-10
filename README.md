````
  ______ ______  ______     _______                   _           _           ___      
 / _____|_____ \|  ___ \   (_______)                 | |         | |         / __)_    
| /      _____) ) | _ | |   _        ____ _   _  ____| |  _       \ \   ___ | |__| |_  
| |     (_____ (| || || |  | |      / ___) | | |/ ___) | / )       \ \ / _ \|  __)  _) 
| \_____      | | || || |  | |_____| |   | |_| ( (___| |< (    _____) ) |_| | |  | |__ 
 \______)     |_|_||_||_|   \______)_|    \____|\____)_| \_)  (______/ \___/|_|   \___)

````
---
# Welcome to CRM TRUCK SOFT

Soft made with Java 17, SpringBoot Framework 2.7.6 and MySql Database, for manage CRM in any Truck Company.

For this version, the soft is managed with Console Comands.

You can manage all the necessary requirements to control the flow of business.

## How to run?

First, need to configure your Database, in file 'application.yaml':

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/YOUR_DATABASE_HERE
    username: USERNAME
    password: PASSWORD
  main:
    banner-mode: "off"
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: create
logging:
  level:
    root: off

```
Run the file Team6CrmApplication with your editor, and enjoy! 

For see all the available commands type 'help' in the 'Insert command:' line.

---
Version: 1.0

Developers:

    - Eleni Taki
    - Pau Tusell
    - Andres Martinez

