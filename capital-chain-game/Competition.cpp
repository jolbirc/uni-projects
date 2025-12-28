#include "Competition.h"
#include <cstddef>
#include <string>
#include <vector>
using namespace std;

// Counts unused cities starting with a given letter
int Competition::countCitiesStartingWith(char letter)
{
    int count = 0;
    const auto& cities = getAllCities();
    const auto& used = getUsedCities();

    for (size_t i = 0; i < cities.size(); i++)
    {
        if (cities[i][0] == letter && !used.contains(cities[i]))
        {
            count++;
        }
    }

    return count;
}


string Competition::getCity(char start_letter)
{
    // Get all valid cities with the required letter
    vector<string> valid_cities;
    const auto& cities = getAllCities();

    for (size_t i = 0; i < cities.size(); i++)
    {
        if (cities[i][0] == start_letter && checkCity(cities[i]))
        {
            valid_cities.push_back(cities[i]);
        }
    }

    if (valid_cities.empty())
    {
        return "";
    }

    // Find the city whose last letter gives opponent the fewest options for better chance of player win
    string best_city = valid_cities.front();
    int best_opponent_options = countCitiesStartingWith(valid_cities[0].back());

    for (size_t i = 1; i < valid_cities.size(); i++)
    {
        char last_letter = valid_cities[i].back();
        int opponent_options = countCitiesStartingWith(last_letter);

        if (opponent_options < best_opponent_options)
        {
            best_opponent_options = opponent_options;
            best_city = valid_cities[i];
        }
    }

    return best_city;
}




