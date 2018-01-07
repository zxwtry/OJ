/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    date:    2018-01-06 14:11:00
*/

select  Person.FirstName as FirstName, 
        Person.LastName as LastName,
        Address.City as City,
        Address.State as State

from    Person
left join Address
on   Person.PersonId = Address.PersonId;
