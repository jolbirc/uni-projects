#pragma once
#include <string>
#include <vector>
#include <random>
#include <unordered_set>
#define ASSIGNMENT_2		//Uncomment to switch to feedback mode for assignment 2

/*
    O notation: fill in your time complexities here
        -	Constructor:    O(n)
        -	getCity:		O(n) - improved
        -	checkCity:      O(n)
        -	markUsed		O(n)
        -	restart			O(n)
*/

class Prototype
{
    public:
        explicit Prototype(const std::string& file_name);
        virtual ~Prototype() = default;
        virtual std::string getCity(char start_letter);
        bool checkCity(const std::string& city);            // Checks if valid
        void markUsed(const std::string& city);
        void restart();
        void seed(int value);
    protected:
        // Made accessors return const references to prevent copying for better efficiency
        const std::vector<std::string>& getAllCities() const { return all_cities; }
        const std::unordered_set<std::string>& getUsedCities() const { return used_cities; }
    private:
        std::vector<std::string> all_cities;
        std::unordered_set<std::string> used_cities;    // Changed to hashmap for faster lookup
        std::mt19937 rng;
};