2 hour coding challenge for kuali
I decided to use Java because I think this is a Object Oriented Proggramming project. Basically, there are several core objects in this system: elevator system, elevators, requests...

And we need to use this system to handle things like: requests(could be divided to internal requerst(in the car) and external request(in the building),open/close gate, report the status(movement, close, open gate...), check trips() and so forth.

Something to think about the design:
1.I used an elevator controller (class elevator system) to find the optimal solution (for external request)
2.I added a function called setNumofElevators to scale up/down the number of elevators in the system.

