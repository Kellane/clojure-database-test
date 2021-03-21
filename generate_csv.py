"""
Generates a .csv with N rows, with (eid, description).

Execute:
    python3 generate_csv N
"""

import csv
import sys

n = sys.argv[1]

with open("data.csv", "w") as data_file:
    csv_writer = csv.writer(data_file)

    for i in range(int(n)):
        csv_writer.writerow([
            i,
            "Eu exercitation excepteur ut aute anim velit ex ullamco ad quis.",
        ])
