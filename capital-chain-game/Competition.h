#pragma once
#include "Prototype.h"

#define Competition_class Competition		//change to Competition once you've implemented your Competition class, defaults to Prototype

class Competition : public Prototype
{
    public:
        explicit Competition(const std::string& file_name) : Prototype(file_name) {}
        Competition() : Prototype("Cities.txt") {}
        std::string getCity(char start_letter) override;
    private:
        int countCitiesStartingWith(char letter);
};