Write-Host "Running Maven clean install..."
mvn -q clean install

if ($LASTEXITCODE -ne 0) {
    Write-Host "Build failed!"
    exit $LASTEXITCODE
}

Write-Host "Build successful!"


Write-Host "Checking if Docker is running..."

docker info > $null 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host "Docker is running!"
} else {
    Write-Host "Docker is not running!"
}


$containerName = "API1"
$exists = docker ps -a --filter "name=^${containerName}$" --format "{{.Names}}"

if ($exists -eq $containerName) {
    Write-Host "Container '$containerName' exists. Removing..."
    docker rm -f $containerName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Container '$containerName' removed successfully."
    } else {
        Write-Host "Failed to remove container '$containerName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Container '$containerName' does not exist. Continuing..."
}

$imageName = "api:latest"

# Check if the image exists
$exists = docker images --format "{{.Repository}}:{{.Tag}}" | Where-Object { $_ -eq $imageName }

if ($exists) {
    Write-Host "Image '$imageName' exists. Removing..."
    docker rmi -f $imageName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Image '$imageName' removed successfully."
    } else {
        Write-Host "Failed to remove image '$imageName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Image '$imageName' does not exist. Continuing..."
}


$containerName = "SCHEDULER1"
$exists = docker ps -a --filter "name=^${containerName}$" --format "{{.Names}}"

if ($exists -eq $containerName) {
    Write-Host "Container '$containerName' exists. Removing..."
    docker rm -f $containerName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Container '$containerName' removed successfully."
    } else {
        Write-Host "Failed to remove container '$containerName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Container '$containerName' does not exist. Continuing..."
}

$imageName = "scheduler:latest"

# Check if the image exists
$exists = docker images --format "{{.Repository}}:{{.Tag}}" | Where-Object { $_ -eq $imageName }

if ($exists) {
    Write-Host "Image '$imageName' exists. Removing..."
    docker rmi -f $imageName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Image '$imageName' removed successfully."
    } else {
        Write-Host "Failed to remove image '$imageName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Image '$imageName' does not exist. Continuing..."
}



$containerName = "RABBITMQ1"
$exists = docker ps -a --filter "name=^${containerName}$" --format "{{.Names}}"

if ($exists -eq $containerName) {
    Write-Host "Container '$containerName' exists. Removing..."
    docker rm -f $containerName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Container '$containerName' removed successfully."
    } else {
        Write-Host "Failed to remove container '$containerName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Container '$containerName' does not exist. Continuing..."
}

$imageName = "rabbitmq:3-management"

# Check if the image exists
$exists = docker images --format "{{.Repository}}:{{.Tag}}" | Where-Object { $_ -eq $imageName }

if ($exists) {
    Write-Host "Image '$imageName' exists. Removing..."
    docker rmi -f $imageName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Image '$imageName' removed successfully."
    } else {
        Write-Host "Failed to remove image '$imageName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Image '$imageName' does not exist. Continuing..."
}



$containerName = "EXECUTOR1"
$exists = docker ps -a --filter "name=^${containerName}$" --format "{{.Names}}"

if ($exists -eq $containerName) {
    Write-Host "Container '$containerName' exists. Removing..."
    docker rm -f $containerName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Container '$containerName' removed successfully."
    } else {
        Write-Host "Failed to remove container '$containerName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Container '$containerName' does not exist. Continuing..."
}

$imageName = "executor:latest"

# Check if the image exists
$exists = docker images --format "{{.Repository}}:{{.Tag}}" | Where-Object { $_ -eq $imageName }

if ($exists) {
    Write-Host "Image '$imageName' exists. Removing..."
    docker rmi -f $imageName > $null 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Image '$imageName' removed successfully."
    } else {
        Write-Host "Failed to remove image '$imageName'."
        exit $LASTEXITCODE
    }
} else {
    Write-Host "Image '$imageName' does not exist. Continuing..."
}






Write-Host "Starting services from docker-compose.yaml..."
docker compose -f docker-compose.yaml up -d > $null 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host "Services started successfully."
} else {
    Write-Host "Failed to start services with docker-compose."
    exit $LASTEXITCODE
}
