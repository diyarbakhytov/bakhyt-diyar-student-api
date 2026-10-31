#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
LOG_FILE="$SCRIPT_DIR/../logs/Bakhyt_Diyar_system_report.log"

{
echo "=============================================="
echo "System Report"
echo "=============================================="
echo "Student: Diyar Bakhyt"
echo "Date and time: $(date '+%Y-%m-%d %H:%M:%S')"
echo "Username: $(whoami)"
echo "Operating system: $(. /etc/os-release && echo "$PRETTY_NAME")"
echo "Kernel version: $(uname -r)"
echo "CPU: $(lscpu | awk -F': +' '/Model name/ {print $2; exit}') ($(nproc) cores)"
echo "Memory: $(free -h | awk '/^Mem:/ {print $3 " used of " $2 ", " $7 " available"}')"
echo "Disk usage: $(df -h / | awk 'NR==2 {print $3 " used of " $2 " (" $5 ")"}')"
echo "Available disk space: $(df -h / | awk 'NR==2 {print $4}')"
echo
} | tee -a "$LOG_FILE"
