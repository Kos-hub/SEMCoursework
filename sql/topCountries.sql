-- 40455050

-- This is a file as part of my exploratory SQL queries to gather the required data for the SEM coursework.

-- As a member of the team I want to produce a list of all the countries in the world, continent, or region organised by largest population to smallest so that the requirements of the coursework are met.


-- of world
SELECT
  name
  ,population
FROM
  country
ORDER BY
  population DESC;


-- of continent
SELECT
  name
  ,population
FROM
  country
WHERE
  continent LIKE "Asia"
ORDER BY
  population DESC;


-- of region
SELECT
  name
  ,population
FROM
  country
WHERE
  region LIKE "Southeast Asia"
ORDER BY
  population DESC;
