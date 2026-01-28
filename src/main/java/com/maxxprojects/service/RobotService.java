package com.maxxprojects.service;

import com.maxxprojects.model.Robot;
import com.maxxprojects.model.RobotStatus;

import java.util.ArrayList;
import java.util.List;

public class RobotService {

    private final List<Robot> robots = new ArrayList<>();
    private long counter = 1;

    public Robot registerRobot(double speed, int batteryLevel) {
        Robot robot = new Robot();
        robot.setId(counter++);
        robot.setSpeed(speed);
        robot.setBatteryLevel(batteryLevel);
        robot.setStatus(RobotStatus.AVAILABLE);

        robots.add(robot);
        return robot;
    }

    public Robot assignAvailableRobot() {
        Robot robot = robots.stream()
                .filter(r -> r.getStatus() == RobotStatus.AVAILABLE)
                .filter(r -> r.getSpeed() <= 15)
                .filter(r -> r.getBatteryLevel() > 20)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available robot"));

        robot.setStatus(RobotStatus.IN_TRANSIT);
        return robot;
    }

    public void updateRobotStatus(Long robotId, RobotStatus status) {
        Robot robot = getRobotById(robotId);
        robot.setStatus(status);
    }

    public Robot getRobotById(Long robotId) {
        return robots.stream()
                .filter(r -> r.getId().equals(robotId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Robot not found"));
    }

    public List<Robot> getAllRobots() {
        return robots;
    }
}
