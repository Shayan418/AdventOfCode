#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <set>
#include <sstream>
#include <string>
#include <vector>

std::vector<std::string> split(const std::string& str, char delimiter) {
    std::vector<std::string> tokens;
    std::string token;
    std::istringstream token_stream(str);
    while (std::getline(token_stream, token, delimiter)) {
        tokens.push_back(token);
    }
    return tokens;
}

void part1(std::ifstream& infile) {

    std::string line;
    int sum = 0;
    std::vector<int> pairs;

    while (getline(infile, line)) {

        std::vector<std::string> tokens = split(line, ',');
        for (const std::string& token : tokens) {
            std::vector<std::string> subtokens = split(token, '-');
            for (const std::string& subtoken : subtokens) {
                std::cout << subtoken << '\n';
                pairs.push_back(stoi(subtoken));
            }
        }

        if (pairs.at(0) <= pairs.at(2) && pairs.at(1) >= pairs.at(3)) {
            sum++;
        } else if (pairs.at(0) >= pairs.at(2) && pairs.at(1) <= pairs.at(3)) {
            sum++;
        }

        pairs.clear();
    }
    infile.close();

    std::cout << sum;
}

void part2(std::ifstream& infile) {
    std::string line;
    int sum = 0;
    std::vector<int> pairs;

    while (getline(infile, line)) {

        std::vector<std::string> tokens = split(line, ',');
        for (const std::string& token : tokens) {
            std::vector<std::string> subtokens = split(token, '-');
            for (const std::string& subtoken : subtokens) {
                std::cout << subtoken << '\n';
                pairs.push_back(stoi(subtoken));
            }
        }

        if (pairs.at(1) < pairs.at(2) || pairs.at(3) < pairs.at(0)) {
            pairs.clear();
            continue;
        }

        sum++;
        pairs.clear();
    }
    infile.close();

    std::cout << sum;
}
int main() {
    std::ifstream fptr("input.txt");

    if (!fptr.is_open()) {
        std::cerr << "Error: unable to open file." << std::endl;
        return 1;
    }

    part2(fptr);

    return 0;
}
