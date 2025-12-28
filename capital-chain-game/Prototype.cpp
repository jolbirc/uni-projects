#include "Prototype.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstddef>      // Header for size_t
#include <random>
#include <unordered_set>
using namespace std;

// Init
Prototype::Prototype(const std::string& file_name)
{
    ifstream reader(file_name);

    string city;
    all_cities.clear();
    used_cities.clear();

    if (!reader)
    {
        cout << "Error opening file." << '\n';
        return;
    }

    // for (int i = 0; !reader.eof(); i++)
    // {
    //     getline(reader, city);
    //     all_cities.push_back(city);
    // }

    // Switching to while loop as index not used previously
    while (getline(reader, city))
    {
        if (!city.empty())
        {
            all_cities.push_back(city);
        }
    }

    reader.close();
}

// Return string containing capital city beginning with given character
string Prototype::getCity(char start_letter)
{
    vector<string> valid_cities = {};

    // Populate valid cities by starting letter
    for (const auto& city : all_cities)
    {
        if (city[0] == start_letter && !used_cities.contains(city))
        {
            valid_cities.push_back(city);
        }
    }

    if (valid_cities.empty())
    {
        return "";
    }

    // Return random city from valid cities: Updated using new seed method and uniform distribution
    uniform_int_distribution<int> dist(0, static_cast<int>(valid_cities.size()) - 1);
    int index = dist(rng);

    return valid_cities[index];
}

// Check if given city is valid and unused
bool Prototype::checkCity(const std::string& city)
{
    if (used_cities.contains(city))
    {
        return false;
    }

    for (size_t i = 0; i < all_cities.size(); i++)
    {
        if (city == all_cities[i])
        {
            return true;
        }
    }

    return false;
}

// Mark given city as used
void Prototype::markUsed(const std::string& city)
{
    if (checkCity(city))            // still O(n)
    {
        used_cities.insert(city);   // now O(1)
    }
}

// Mark all cities unused
void Prototype::restart()
{
    used_cities.clear();
}

// Seeding method
void Prototype::seed(int value)
{
    rng.seed(value);
}


