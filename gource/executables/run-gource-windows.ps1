# Variables
$original_file = "./gource/logging/gourcelog.log"
$destination_file =  "./gource/logging/gourcelog.log"

# Go to the correct folder
cd ..
cd ..

# Generate the log in Gource format
git log --pretty=format:user:%aN%n%ct --reverse --raw --encoding=UTF-8 --no-renames | Out-File -Encoding ASCII ./gource/logging/gourcelog.log

# Replace usernames to correct 
(Get-Content $original_file) | Foreach-Object {
    $_ -replace "user:Lisette V", "user:Lissette Veldkamp" `
       -replace "user:IJoshFTW", "user:Joshua Slik" `
       -replace "user:sabenoist", "user:Sander Benoist" `
       -replace "user:Sander Arjan Benoist", "user:Sander Benoist" `
    } | Set-Content $destination_file

# Run Gource
gource ./gource/logging/gourcelog.log --load-config ./gource/config/gource-config.ini
Exit