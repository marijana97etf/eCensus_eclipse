SELECT COUNT(*) AS Broj
FROM STANOVNIK stanovnik
INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB
WHERE popisnicaStanovnika.IdEntiteta like 0 AND popisnicaStanovnika.IdOpstine like 0 AND Pol like "%" AND YEAR(CURDATE()) - CONVERT(CONCAT(if(SUBSTRING(stanovnik.JMB,4,1) >= "8","1","2"),SUBSTRING(stanovnik.JMB,5,3)), UNSIGNED INTEGER) = 0;