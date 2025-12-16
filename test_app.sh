#!/bin/bash

# Test script for British Spoken Time Converter
echo "Testing British Spoken Time Converter..."

# Test cases
test_cases=(
    "09:15"
    "14:30"
    "00:00"
    "12:00"
    "23:59"
    "exit"
)

# Create input file
input_file=$(mktemp)
for test_case in "${test_cases[@]}"; do
    echo "$test_case" >> "$input_file"
done

# Run the application
echo "Running application with test inputs..."
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar < "$input_file"

# Cleanup
rm "$input_file"

echo "Test completed!"