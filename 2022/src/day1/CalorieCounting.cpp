#include <algorithm>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>

int main() {
    std::ifstream infile("input.txt");

    if (!infile.is_open()) {
        std::cerr << "Error: unable to open file." << std::endl;
        return 1;
    }

    std::string line;
    int max = 0, sum = 0;
    std::vector<int> sumVec;

    while (getline(infile, line)) {
        if (line.empty()) {
            max = std::max(max, sum);
            sumVec.push_back(sum);
            sum = 0;
        } else {
            sum += stoi(line);
        }
    }

    infile.close();

    std::cout << max;

    std::sort(sumVec.begin(), sumVec.end());
    int topsum = 0;
    for (int i = 1; i <= 3; i++) {
        topsum += sumVec.at(sumVec.size() - i);
    }
    std::cout << std::endl << topsum;

    return 0;
}
