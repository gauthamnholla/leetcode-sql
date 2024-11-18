# Write your MySQL query statement below
select w1.id
from Weather w1
join weather w2
    on w1.recordDate = Date_Add(w2.recordDate, INTERVAL 1 DAY)
Where w1.temperature > w2.temperature;