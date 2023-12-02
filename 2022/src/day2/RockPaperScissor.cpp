#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <vector>

std::map<char, int> m = {{'A', 1}, {'B', 2}, {'C', 3},
                         {'X', 1}, {'Y', 2}, {'Z', 3}};
std::map<int, int> mWin = {{1, 2}, {2, 3}, {3, 1}};
std::map<int, int> mLoose = {{2, 1}, {3, 2}, {1, 3}};

int winnner(int a, int b) {
    if ((a == 1 && b == 2) || (a == 3 && b == 1) || (a == 2 && b == 3)) {
        return 1;
    }
    if (a == b) {
        return -1;
    }
    return 0;
}

int winnnerMOD(int a, int b) {
    if (b == 2) {
        return a;
    } else if (b == 3) {
        return mWin[a];
    } else {
        return mLoose[a];
    }

    return 0;
}

int main() {
    std::ifstream infile("input.txt");

    if (!infile.is_open()) {
        std::cerr << "Error: unable to open file." << std::endl;
        return 1;
    }

    std::string line;
    int score = 0;
    while (getline(infile, line)) {
        // std::cout << line[0] << " " << line[2] << std::endl;
        // int myPlay = m[line[2]];
        int myPlay = winnnerMOD(m[line[0]], m[line[2]]);
        int gameState = winnner(m[line[0]], myPlay);

        if (gameState == 1) {
            score += 6 + myPlay;
        } else if (gameState == -1) {
            score += 3 + myPlay;
        } else {
            score += myPlay;
        }
    }

    infile.close();
    std::cout << score;
    return 0;
}
