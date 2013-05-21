def makeCounter() {
	def very_local_variable = 0
	return { return very_local_variable += 1 }
}

c1 = makeCounter()
c1()
c1()
c1()

c2 = makeCounter()

println "C1 = ${c1()}, C2 = ${c2()}"
// output: C1 = 4, C2 = 1