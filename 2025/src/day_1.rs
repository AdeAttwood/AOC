pub fn part_1() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_1.part_1.txt"
    ));

    let result = input
        .trim()
        .split("\n")
        .scan(50, |state, item| {
            let num = match item.as_bytes() {
                [b'L', n @ ..] => -str::from_utf8(n).unwrap().parse::<i32>().unwrap(),
                [b'R', n @ ..] => str::from_utf8(n).unwrap().parse::<i32>().unwrap(),
                _ => panic!("How are you here"),
            };

            *state = (*state + num).rem_euclid(100);
            Some(*state)
        })
        .filter(|x| *x == 0)
        .count();

    println!("Result Part 1: {result}");
}

pub fn part_2() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_1.part_1.txt"
    ));

    let result: i32 = input
        .trim()
        .split("\n")
        .scan(50, |state, item| {
            let num = match item.as_bytes() {
                [b'L', n @ ..] => vec![-1; str::from_utf8(n).unwrap().parse::<usize>().unwrap()],
                [b'R', n @ ..] => vec![1; str::from_utf8(n).unwrap().parse::<usize>().unwrap()],
                _ => panic!("How are you here"),
            };

            let mut x = 0;
            for i in num {
                *state = ((*state + i) as i32).rem_euclid(100);
                if *state == 0 {
                    x += 1;
                }
            }

            Some(x)
        })
        .sum();

    println!("Result Part 2: {result}");
}
