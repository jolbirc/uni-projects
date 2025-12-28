#pragma once
#include <concepts>
#include <vector>
#include <list>
#include <string>

//------------------------------------------------Prototype----------------------------------------------

template<class T>
concept hasGetCity = requires(T t) {
	{t.getCity(std::declval<char>())} -> std::convertible_to<std::string>;
};

template<class T>
concept hasCheckCityVal = requires(T t) {
	{t.checkCity(std::declval<std::string>())} -> std::convertible_to<bool>;
};

template<class T>
concept hasCheckCityRef = requires(T t) {
    {t.checkCity(std::declval<std::string&>())} -> std::convertible_to<bool>;
};

template<class T>
concept hasMarkUsedVal = requires(T t) {
	{t.markUsed(std::declval<std::string>())} -> std::convertible_to<void>;
};

template<class T>
concept hasMarkUsedRef = requires(T t) {
    {t.markUsed(std::declval<std::string&>())} -> std::convertible_to<void>;
};

template<class T>
concept hasRestart = requires(T t) {
	{t.restart()} -> std::same_as<void>;
};

template<class T>
concept hasConstructor = std::constructible_from<T, std::string> || std::constructible_from<T, std::string&>;

template<class T>
concept hasMarkUsed = hasMarkUsedVal<T> || hasMarkUsedRef<T>;

template<class T>
concept hasCheckCity = hasCheckCityVal<T> || hasCheckCityRef<T>;

template<class T>
concept allPrototypeMethods = (hasMarkUsed<T> && hasCheckCity<T> && hasGetCity<T> && hasRestart<T> && hasConstructor<T>);

template<class T>
concept hasSeed = requires(T t) {
	{t.seed(std::declval<int>())} -> std::same_as<void>;
};

template<class T>
concept allProjectCityMethods = (allPrototypeMethods<T> && hasSeed<T>);

//-------------------------------------------------Simulator---------------------------------------------------

template<class T>
concept hasRunVal = requires(T t) {
	{t.run(std::declval<std::string>(), std::declval<char>(), std::declval<int>())} -> std::convertible_to<std::list<std::string> >;
};

template<class T>
concept hasRunRef = requires(T t) {
    {t.run(std::declval<std::string&>(), std::declval<char>(), std::declval<int>())} -> std::convertible_to<std::list<std::string> >;
};

template<class T>
concept hasBatchVal = requires(T t) {
	{t.batch(std::declval<std::string>(), std::declval<int>(), std::declval<int>())} -> std::convertible_to<double>;
};

template<class T>
concept hasBatchRef = requires(T t) {
    {t.batch(std::declval<std::string&>(), std::declval<int>(), std::declval<int>())} -> std::convertible_to<double>;
};

template<class T>
concept hasGetResults = requires(T t) {
	{t.getResults()} -> std::convertible_to < std::vector<std::list<std::string> > >;
};

template<class T>
concept hasRun = hasRunVal<T> || hasRunRef<T>;

template<class T>
concept hasBatch = hasBatchVal<T> || hasBatchRef<T>;

template<class T>
concept allSimulatorMethods = (hasRun<T> && hasBatch<T> && hasGetResults<T>);