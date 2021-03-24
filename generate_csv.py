"""
Generates a .csv with N rows, with (eid, description).

Execute:
    python3 generate_csv N
"""

import csv
import sys

# n = sys.argv[1]
eid = 0

with open("/tmp/data.csv", "r") as src_file:
    with open("data.csv", "w") as data_file:
        csv_writer = csv.writer(data_file)
        csv_reader = csv.reader(src_file)


        skip_header = True
        for row in csv_reader:
            if skip_header:
                skip_header = False
                continue

            print(row[0])
            csv_writer.writerow([eid, str(row[0])])
            eid += 1
