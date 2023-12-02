#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <set>
#include <string>
#include <vector>

int priorityChecker(char c) {
    if (c >= 'a') {
        return c - 96;
    } else {
        return c - 38;
    }
}

void part1(std::ifstream& infile) {

    std::string line;
    int length;
    int sum = 0;
    while (getline(infile, line)) {
        length = line.length();
        std::cout << line << " : " << line.length() << " : ";
        for (int i = 0; i < length / 2; i++) {
            for (int j = length / 2; j < length; j++) {
                if (line[i] == line[j]) {
                    std::cout << line[i] << std::endl;
                    sum += priorityChecker(line[i]);
                    goto stopLoop;
                }
            }
        }

    stopLoop : {}
    }
    infile.close();

    std::cout << sum;
}

void part2(std::ifstream& infile) {
    std::string line;
    std::vector<std::set<char>> data;
    int sum = 0;

    while (getline(infile, line)) {
        std::set<char> lineData(begin(line), end(line));

        data.push_back(lineData);
    }
    infile.close();

    for (long long unsigned int i = 0; i < data.size(); i += 3) {
        char arr1[3][54] = {0};
        // std::cout << "Set " << i << ": {";
        for (int k = 0; k < 3; k++) {
            for (char j : data[i + k]) {
                // std::cout << j << " ";
                if (j <= 'Z') {
                    arr1[k][j - 65] = j;
                } else {
                    arr1[k][j - 71] = j;
                }
            }
        }
        // std::cout << "}" << std::endl;
        for (int i = 0; i < 54; ++i) {
            if (arr1[0][i] && arr1[1][i] && arr1[2][i]) {
                if ((arr1[0][i] == arr1[1][i]) && arr1[1][i] == arr1[2][i]) {
                    // std::cout << arr1[0][i] << std::endl;
                    sum += priorityChecker(arr1[0][i]);
                    goto stopLoop;
                }
            }
        }
        // std::cout << std::endl;
    stopLoop : {}
    }

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
