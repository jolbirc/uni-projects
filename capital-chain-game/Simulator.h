#pragma once
#include "Prototype.h"
#include <string>
#include <vector>
#include <list>
#include <mutex>

class Simulator
{
    public:
        Simulator();
        static std::list<std::string> run(const std::string& file_name, char start_letter, int seed);   // Return words used in sim game
        double batch(const std::string& file_name, int k, int seed);   // Run k sims, return time taken in s
        std::vector<std::list<std::string>> getResults();
    private:
        std::vector<std::list<std::string>> all_results;
        std::mutex all_results_mutex;
};