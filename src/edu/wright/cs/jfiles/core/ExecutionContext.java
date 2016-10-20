/*
 * Copyright (C) 2016 - WSU CEG3120 Students
 * 
 * Roberto C. Sánchez <roberto.sanchez@wright.edu>
 * 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.wright.cs.jfiles.core;

/**
 * Class used to hold the context for command execution.
 *
 */
public class ExecutionContext {

	private Environment environment;
	private ExecutablePath path;

	/**
	 * Sets the context for the execution environment.
	 * 
	 * @param environment
	 *            the current environment
	 * @param path
	 *            the path of the executable
	 */
	public ExecutionContext(Environment environment, ExecutablePath path) {
		this.environment = environment;
		this.path = path;
	}

	/**
	 * Gets the path of the executable.
	 * 
	 * @return the path of the executable
	 */
	public ExecutablePath getPath() {
		return path;
	}

	/**
	 * Gets the current environment.
	 * 
	 * @return the current environment
	 */
	public Environment getEnvironment() {
		return environment;

	}
}
